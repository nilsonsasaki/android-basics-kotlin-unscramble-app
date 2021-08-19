package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel:ViewModel() {
    private var _score = 0
    val score:Int get() =_score
    private var currentWordCount = 0
    private lateinit var _currentScrambledWord :String

    private var wordsList : MutableList<String> = mutableListOf()
    private lateinit var currentWord: String

    val currentScrambledWord: String
        get() = _currentScrambledWord

    init {
        Log.d("GameFragment","GameViewModel created")
        getNextWord()
    }

    private fun getNextWord() {
        do{
            currentWord = allWordsList.random()
        }while (wordsList.contains(currentWord))

        val tempWord = currentWord.toCharArray()
        tempWord.shuffle()

        while (tempWord.toString().equals(currentWord, false)) {
            tempWord.shuffle()
        }

        _currentScrambledWord = String(tempWord)
        ++currentWordCount
        wordsList.add(currentWord)

    }

    fun nextWord(): Boolean {
        return if (currentWordCount < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else false
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("GameFragment","GameViewModel destroyed!")
    }
}