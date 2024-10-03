package co.thiennguyen.github_profile.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import co.thiennguyen.github_profile.domain.models.User

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val loginUserName: String,
    val htmlUrl: String,
    val avatarUrl: String
)

private fun UserEntity.toModel() = User(
    login = this.loginUserName,
    htmlUrl = this.htmlUrl,
    avatarUrl = this.avatarUrl,
)

fun List<UserEntity>.toModels() = this.map { it.toModel() }

private fun User.toEntity() = UserEntity(
    loginUserName = this.login,
    htmlUrl = this.htmlUrl,
    avatarUrl = this.avatarUrl,
)

fun List<User>.toEntities() = this.map { it.toEntity() }