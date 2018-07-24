package com.tech.transaction.fragmentUtil

import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

/**
 * Start an Activity for given class T and allow to work on intent with "run" lambda function
 * @params arguments
 */
fun <T: Fragment> T.withArguments(vararg arguments: Pair<String, Parcelable>): T {
    val bundle = Bundle()
    arguments.forEach { bundle.putParcelable(it.first, it.second) }
    this.arguments = bundle
    return this
}

/**
 * Retrieve property from intent
 * @param key
 */
fun <T1 : Any, T2: FragmentActivity> T2.argument(key: String) = lazy { intent.extras[key] as T1 }

/**
 * Retrieve property with default value from intent
 * @param key
 * @param defaultValue
 */
fun <T1 : Any, T2 : FragmentActivity> T2.argument(key: String, defaultValue: T1? = null) = lazy {
    intent.extras[key] as? T1 ?: defaultValue
}

/**
 * Retrieve property from intent
 * @param key
 */
fun <T1 : Any> Fragment.argument(key: String) = lazy { arguments?.get(key) as T1 }

/**
 * Retrieve property with default value from intent
 * @param key
 * @param defaultValue
 */
fun <T1 : Any, T2: Fragment> T2.argument(key: String, defaultValue: T1? = null) = lazy {
    arguments?.get(key)  as? T1 ?: defaultValue
}
