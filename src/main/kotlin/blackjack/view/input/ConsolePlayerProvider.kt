package blackjack.view.input

import blackjack.model.player.Player
import blackjack.model.player.PlayerBet
import blackjack.model.player.PlayerProvider
import blackjack.model.player.Players
import blackjack.model.player.Players.Companion.toPlayers
import blackjack.view.input.parser.IntInputParser
import blackjack.view.input.parser.PlayerNamesInputParser

class ConsolePlayerProvider : PlayerProvider {

    private val playNamesInputParser = PlayerNamesInputParser()
    private val hitDecisionMaker = ConsoleHitDecisionMaker()
    private val betMoneyInputParser = IntInputParser(minValue = PlayerBet.MIN_BET_MONEY)

    override fun createGuestPlayers(): Players<Player.Guest> =
        ConsoleReader.read(MESSAGE_INPUT_PLAYERS, playNamesInputParser).map { name ->
            Player.Guest(name = name, hitDecisionMaker = hitDecisionMaker)
        }.toPlayers()

    override fun betForPlayer(player: Player.Guest): Int = readBetMoneyFor(player.name)

    private fun readBetMoneyFor(name: String): Int =
        ConsoleReader.read("${name}의 배팅 금액은?", betMoneyInputParser)

    companion object {
        private const val MESSAGE_INPUT_PLAYERS = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    }
}