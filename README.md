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
   
2) Настройки (добавить из  мастера)

Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(50.dp))
                Spacer(modifier = Modifier.width(20.dp))
                Box(
                    modifier = Modifier
                        .padding(15.dp)
                        .width(370.dp)
                        .height(150.dp)
                        .border(2.dp, Color.Black),
                    contentAlignment = Alignment.Center
                ) {
                    Row() {
                        Text(
                            textAlign = TextAlign.Center,
                            color = Color.Black,
                            text = "Unlimited",
                            fontSize = 14.sp,

                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        var darkMode by remember { mutableStateOf(true) }
                        Text(
                            color = Color.Black,
                            text = "Unlimited",
                            fontSize = 14.sp,

                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                        )
                        Spacer(modifier = Modifier.width(15.dp))
                        Switch(checked = darkMode, onCheckedChange = { darkMode = !darkMode })

                    }
                }

                Spacer(modifier = Modifier.height(50.dp))
                Spacer(modifier = Modifier.width(20.dp))
                Box(
                    modifier = Modifier
                        .padding(15.dp)
                        .width(370.dp)
                        .height(150.dp)
                        .border(2.dp, Color.Black),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Coding with Rashid",
                    )
                }

                Spacer(modifier = Modifier.height(50.dp))
                Spacer(modifier = Modifier.width(20.dp))
                Box(
                    modifier = Modifier
                        .padding(15.dp)
                        .width(370.dp)
                        .height(150.dp)
                        .border(2.dp, Color.Black),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Coding with Rashid",
                    )
                }

                Spacer(modifier = Modifier.height(50.dp))
                Spacer(modifier = Modifier.width(20.dp))
                Box(
                    modifier = Modifier
                        .padding(15.dp)
                        .width(370.dp)
                        .height(150.dp)
                        .border(2.dp, Color.Black),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Coding with Rashid",
                    )
                }
            }
