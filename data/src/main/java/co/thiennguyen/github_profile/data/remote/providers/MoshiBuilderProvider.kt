package co.thiennguyen.github_profile.data.remote.providers

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.util.Date

object MoshiBuilderProvider {

    val moshiBuilder: Moshi.Builder
        get() = Moshi.Builder()
            // Parse the DateTime in this format: [yyyy-MM-ddThh:mm:ss.ssZ]
            .add(Date::class.java, Rfc3339DateJsonAdapter())
            .add(KotlinJsonAdapterFactory())
}
