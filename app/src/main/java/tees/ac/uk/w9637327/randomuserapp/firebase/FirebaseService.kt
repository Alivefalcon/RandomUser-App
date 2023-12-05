package tees.ac.uk.w9637327.randomuserapp.firebase

import com.google.firebase.auth.FirebaseAuth

fun LoginFirebase(email:String, password: String, onResult: (Boolean) -> Unit) {
    FirebaseAuth
        .getInstance()
        .signInWithEmailAndPassword(
            email,
            password
        )
        .addOnCompleteListener {
            if (it.isSuccessful) {
                onResult(true)
            }
            onResult(false)
        }
        .addOnFailureListener {
            onResult(false)
        }
}

fun CreateUserInFirebase(email: String, password: String, onResult: (Boolean) -> Unit) {

    FirebaseAuth
        .getInstance()
        .createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener {
            if (it.isSuccessful) {
                onResult(true)
            }
            onResult(false)
        }
        .addOnFailureListener {
            onResult(false)
        }
}