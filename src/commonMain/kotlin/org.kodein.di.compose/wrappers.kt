package org.kodein.di.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import org.kodein.di.Copy
import org.kodein.di.DI


@Composable
public fun withDI(builder: DI.MainBuilder.() -> Unit, content: @Composable () -> Unit): Unit =
    CompositionLocalProvider(LocalDI provides DI { builder() }) { content() }

@Composable
public fun withDI(vararg diModules: DI.Module, content: @Composable () -> Unit): Unit =
    CompositionLocalProvider(LocalDI provides DI { importAll(*diModules)}) { content() }

@Composable
public fun withDI(di: DI, content: @Composable () -> Unit): Unit =
    CompositionLocalProvider(LocalDI provides di) { content() }

@Composable
public fun subDI(
    sub: DI.MainBuilder.() -> Unit,
    allowSilentOverride: Boolean = false,
    copy: Copy = Copy.NonCached,
    content: @Composable() () -> Unit
) {
    val di = org.kodein.di.subDI(parentDI = LocalDI.current, allowSilentOverride, copy) { sub() }
    CompositionLocalProvider(LocalDI provides di) { content() }
}
