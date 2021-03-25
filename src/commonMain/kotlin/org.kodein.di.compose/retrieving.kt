package org.kodein.di.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.kodein.di.*

@Composable
public inline fun <reified T : Any> instance(tag: Any? = null): DIProperty<T> = with(LocalDI.current) {
    remember { instance(tag) }
}

@Composable
public inline fun <reified A : Any, reified T : Any> instance(tag: Any? = null, arg: A): DIProperty<T> = with(LocalDI.current) {
    remember { instance(tag, arg) }
}

@Composable
public inline fun <reified A : Any, reified T : Any> factory(tag: Any? = null): DIProperty<(A) -> T> = with(LocalDI.current) {
    remember { factory(tag) }
}

@Composable
public inline fun <reified A : Any, reified T : Any> provider(tag: Any? = null): DIProperty<() -> T> = with(LocalDI.current) {
    remember { provider(tag) }
}

@Composable
public inline fun <reified A : Any, reified T : Any> provider(tag: Any? = null, arg: A): DIProperty<() -> T> = with(LocalDI.current) {
    remember { provider(tag, arg) }
}

@Composable
public inline fun <reified A : Any, reified T : Any> provider(tag: Any? = null, noinline fArg: () -> A): DIProperty<() -> T> = with(LocalDI.current) {
    remember { provider(tag, fArg) }
}