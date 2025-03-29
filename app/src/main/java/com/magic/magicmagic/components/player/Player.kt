package com.magic.magicmagic.components.player

interface IPlayer {
    val name: String
}

class Player(override val name: String) : IPlayer
