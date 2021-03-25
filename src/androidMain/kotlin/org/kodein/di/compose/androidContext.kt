package org.kodein.di.compose

import android.content.Context
import android.content.ContextWrapper
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import org.kodein.di.DI
import org.kodein.di.DIAware

@Composable
public fun contextDI(): DI {
    var context: Context? = LocalContext.current
    while (context != null) {
        if (context is DIAware) return context.di
        context = if (context is ContextWrapper) context.baseContext else null
    }
    return (LocalContext.current.applicationContext as DIAware).di
}

@Composable
public fun withDI(content: @Composable () -> Unit) = CompositionLocalProvider(LocalDI provides contextDI()) { content() }
