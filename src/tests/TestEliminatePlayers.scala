package tests

import main._
import physics._
import org.scalatest._

class TestEliminatePlayers extends FunSuite {

  test("Tests the eliminate player method") {
    val defaultHealth: Int = 20
    val defaultStrength: Int = 5
    val defaultLocation: PhysicsVector = new PhysicsVector(0.0, 0.0)
    val defaultVelocity: PhysicsVector = new PhysicsVector(0.0, 0.0)
    val defaultOrientation: PhysicsVector = new PhysicsVector(1.0, 0.0)

    val player1: Player = new Player(defaultLocation, defaultVelocity, username = "ReadyPlayerOne")
    val player2: Player = new Player(defaultLocation, defaultVelocity, username = "PlayerTwo")
    val player3: Player = new Player(defaultLocation, defaultVelocity, username = "PlayerThree")
    val player4: Player = new Player(defaultLocation, defaultVelocity, username = "PlayerFour")
    val player5: Player = new Player(defaultLocation, defaultVelocity, username = "PlayerFive")

    player3.health = 0
    player5.health = 0

    val playerList: List[Player] = List(player1, player2, player3, player4, player5)
    val world1: World = new World(9.8, List(), playerList, List())
    val newPlayers1: List[Player] = List(player1, player2, player4)
    val world2: World = new World(9.8, List(), newPlayers1, List())
    val world3: World = new World(9.8, List(), List(), List())

    world1.eliminatePlayers()
    world2.eliminatePlayers()
    world3.eliminatePlayers()

    assert(world1.players == newPlayers1)
    assert(world2.players == newPlayers1)
    assert(world3.players == List())
  }
}
