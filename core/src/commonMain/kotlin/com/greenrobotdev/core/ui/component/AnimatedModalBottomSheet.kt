package com.greenrobotdev.core.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun AnimatedModalBottomSheet(
    showSheet: Boolean,
    onDismiss: () -> Unit,
    dragHandle : @Composable (() -> Unit)? =  {
        Surface(
            modifier = Modifier
                .padding(top = 8.dp, bottom = 4.dp)
                .semantics { contentDescription = "dragHandleDescription" },
            color = DividerDefaults.color,
            shape = ButtonDefaults.shape
        ) {
            Box(
                Modifier
                    .size(
                        width = 30.dp,
                        height = 2.dp
                    )
            )
        }

    },
    topBar: @Composable (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val scope: CoroutineScope = rememberCoroutineScope()
    val sheetState: SheetState = rememberModalBottomSheetState()
    var sheetVisible: Boolean by mutableStateOf(showSheet)

    if (showSheet != sheetVisible) {
        if (showSheet) {
            scope.launch { sheetState.show() }
        } else {
            scope.launch { sheetState.hide() }
        }
        sheetVisible = showSheet
    }

    ModalBottomSheet(
        tonalElevation = 20.dp,
        sheetState = sheetState,
        dragHandle = dragHandle,
        scrimColor = Color.Black.copy(alpha = 0.5f),
        onDismissRequest = onDismiss
    ) {
        BottomSheetScaffold(
            topBar = topBar,
            sheetContent = {

            }
        ){
            content()
        }
    }
}