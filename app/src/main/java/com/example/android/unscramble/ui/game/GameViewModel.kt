package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    private var _score = 0
    val score: Int get() = _score

    private var _currentWordCount = 0
    val currentWordCount get() = _currentWordCount

    private var wordsList: MutableList<String> = mutableListOf()
    private lateinit var _currentWord: String
    private val currentWord: String get() = _currentWord

    private lateinit var _currentScrambledWord: String
    val currentScrambledWord: String
        get() = _currentScrambledWord

    init {
        Log.d("GameFragment", "GameViewModel created")
        getNextWord()
    }

    private fun getNextWord() {
        do {
            _currentWord = allWordsList.random()
        } while (wordsList.contains(_currentWord))

        val tempWord = _currentWord.toCharArray()
        tempWord.shuffle()

        while (tempWord.toString().equals(_currentWord, false)) {
            tempWord.shuffle()
        }

        _currentScrambledWord = String(tempWord)
        ++_currentWordCount
        wordsList.add(_currentWord)

    }

    fun nextWord(): Boolean {
        return if (currentWordCount < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else false
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("GameFragment", "GameViewModel destroyed!")
    }
}