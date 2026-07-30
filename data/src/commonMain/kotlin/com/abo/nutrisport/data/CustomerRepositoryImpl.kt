package com.abo.nutrisport.data

import com.abo.nutrisport.data.domain.CustomerRepository
import com.abo.nutrisport.domain.Customer
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.firestore.firestore

class CustomerRepositoryImpl : CustomerRepository {
    override suspend fun createCustomer(
        user: FirebaseUser?,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        try {
            if (user != null) {
                val customerCollection = Firebase.firestore.collection(collectionPath = "customer")
                val customer = Customer(
                    id = user.uid,
                    firstname = user.displayName?.split(" ")?.firstOrNull() ?: "unknown",
                    lastname = user.displayName?.split(" ")?.lastOrNull() ?: "unknown",
                    email = user.email ?: "",
                )
                val customerExists =
                    customerCollection.document(documentPath = user.uid).get().exists
                if (customerExists) {
                    onSuccess()
                } else {
                    customerCollection.document(documentPath = user.uid).set(customer)
                    onSuccess()
                }

            } else
                onError("User is not available")

        } catch (e: Exception) {
            onError("Error while creating a customer: ${e.message}")
        }
    }

}