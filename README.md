# AdversBLE
1) Выдвигалка:
Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                var isVisible by remember {
                    mutableStateOf(false)
                }

                Button(onClick = {
                    isVisible = !isVisible
                }) {
                    Text(text = if (isVisible) "Hide" else "Show")
                }

                AnimatedVisibility(isVisible) {
                    Box(
                        modifier = Modifier
                            .size(300.dp)
                            .padding(20.dp)
                            .background(Color.White)
                    )
                }
            }
   
