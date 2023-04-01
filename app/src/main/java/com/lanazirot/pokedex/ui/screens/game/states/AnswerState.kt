package com.lanazirot.pokedex.ui.screens.game.states

import com.lanazirot.pokedex.domain.models.Answer


sealed class AnswerState {
    class Correct(val answer: Answer) : AnswerState()
    class Incorrect(val answer: Answer) : AnswerState()
    object  None: AnswerState()
}

