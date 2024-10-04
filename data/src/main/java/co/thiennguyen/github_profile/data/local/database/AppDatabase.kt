package co.thiennguyen.github_profile.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import co.thiennguyen.github_profile.data.local.dao.UserDao
import co.thiennguyen.github_profile.data.local.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
