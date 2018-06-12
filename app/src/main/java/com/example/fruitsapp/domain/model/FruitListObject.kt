package com.example.fruitsapp.domain.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class FruitListObject(
        @PrimaryKey open var id: Long = 100,
        open var list: RealmList<Fruit>? =null
): RealmObject() {
}