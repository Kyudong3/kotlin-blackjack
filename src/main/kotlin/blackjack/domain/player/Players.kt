package blackjack.domain.player

class Players(val players: List<Player>) {

    init {
        require(players.isNotEmpty()) {
            "플레이어는 한 명 이상이어야 합니다."
        }
    }

    fun addBaseCards(dealer: Dealer, baseCount: Int) {
        players.forEach { player ->
            repeat(baseCount) { dealer.giveCard(player) }
        }
    }

    fun hittablePlayers(): List<Player> {
        return players.filterNot(Player::isEnd)
    }

    fun isEnd(): Boolean {
        return players.all(Player::isEnd)
    }

    operator fun contains(player: Player): Boolean {
        return players.contains(player)
    }
}