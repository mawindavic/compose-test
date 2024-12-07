package com.mawinda.domain.mappers

import com.mawinda.domain.model.Med
import com.mawinda.remote.model.MedDTO

fun MedDTO.toModel(id: Int): Med = Med(id, name, dose, strength)