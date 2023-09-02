package com.example.fininfocomtask.realm

import com.example.fininfocomtask.BuildConfig
import com.example.fininfocomtask.model.User
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.executeTransactionAwait
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class UserDatabaseOperations @Inject constructor(private val config: RealmConfiguration) {

    suspend fun insertUser(name: String, age: Int, city: String) {
        val realm = Realm.getInstance(config)
        realm.executeTransactionAwait(Dispatchers.IO) { realmTransaction ->
            val user = UserRealm(name = name, age = age, city = city)
            realmTransaction.insert(user)

        }
    }

    suspend fun retrieveUser(): List<User> {
        val realm = Realm.getInstance(config)
        val users = mutableListOf<User>()

        realm.executeTransactionAwait(Dispatchers.IO) { realmTransaction ->
            users.addAll(realmTransaction.where(UserRealm::class.java).findAll().map {
                mapSet(it)
            })

        }

        return users
    }

    fun mapSet(user: UserRealm): User {
        return User(
            id=user.id,
            name = user.name,
            age = user.age,
            city = user.city
        )
    }


}