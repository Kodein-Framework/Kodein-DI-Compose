package org.kodein.di.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import org.kodein.di.DI

public val LocalDI: ProvidableCompositionLocal<DI> = compositionLocalOf<DI> { error("Missing DI container!") }

@Composable
public fun localDI(): DI = LocalDI.current