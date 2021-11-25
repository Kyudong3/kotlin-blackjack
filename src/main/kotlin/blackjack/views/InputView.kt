package blackjack.views

import blackjack.domain.player.Player

object InputView {

    tailrec fun askGamerNames(): String {
        println(PLAYER_NAMES_QUESTION)
        return readLine() ?: askGamerNames()
    }

    tailrec fun askGamerReceiveMoreCard(player: Player): String {
        val name = player.getPlayerName()
        println("$name$RECEIVE_MORE_CARD_QUESTION")
        val readLine = readLine()
        if (readLine.isNullOrBlank() || (readLine != RECEIVE_MORE_CARD_INPUT && readLine != RECEIVE_NO_MORE_CARD_INPUT)) {
            return askGamerReceiveMoreCard(player)
        }
        return readLine
    }

    private const val PLAYER_NAMES_QUESTION = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val RECEIVE_MORE_CARD_QUESTION = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
    private const val RECEIVE_MORE_CARD_INPUT = "y"
    private const val RECEIVE_NO_MORE_CARD_INPUT = "n"
}