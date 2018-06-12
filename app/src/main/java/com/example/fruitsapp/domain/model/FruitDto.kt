package com.example.fruitsapp.domain.model

data class FruitDto(
        val id: Long,
        val name: String,
        val color: String,
        val weight: Long,
        val delicious: Boolean,
        val created_at: String,
        val updated_at: String
) {
    fun createModel(): Fruit {
        return Fruit(
                id = id,
                name = name,
                color = color,
                delicious = delicious,
                weight = weight,
                created_at = created_at,
                updated_at = updated_at)
    }
}