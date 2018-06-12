package com.example.fruitsapp.domain.model

import com.github.nitrico.lastadapter.StableId
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Fruit(
        @PrimaryKey public open var id: Long = 0,
        var name: String? = null,
        var color: String? = null,
        var weight: Long = 0,
        var delicious: Boolean = false,
        var created_at: String? = null,
        var updated_at: String? = null
): RealmObject(), StableId {
    override val stableId: Long
        get() = id.hashCode().toLong()
}