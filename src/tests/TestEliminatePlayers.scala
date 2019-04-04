package tests

import main._
import physics._
import org.scalatest._

import scala.collection.mutable.ListBuffer

class TestEliminatePlayers extends FunSuite {

  test("Tests the eliminate player method") {

    val defaultLocation: PhysicsVector = new PhysicsVector(0.0, 0.0)
    val defaultVelocity: PhysicsVector = new PhysicsVector(0.0, 0.0)

    val boundaries: List[Boundary] = List.empty

    val world1: World = new World(boundaries)
    val world2: World = new World(boundaries)
    val world3: World = new World(boundaries)

    val player1: Player = new Player(defaultLocation, defaultVelocity, username = "ReadyPlayerOne", world1)
    val player2: Player = new Player(defaultLocation, defaultVelocity, username = "PlayerTwo", world1)

    world1.players += player1
    world1.players += player2

    player2.health = 0

    val player3: Player = new Player(defaultLocation, defaultVelocity, username = "PlayerThree", world2  )
    val player4: Player = new Player(defaultLocation, defaultVelocity, username = "PlayerFour", world2)

    world2.players += player3
    world2.players += player4

    val player5: Player = new Player(defaultLocation, defaultVelocity, username = "PlayerFive", world3)

    world3.players += player5

    player5.health = 0


    world1.eliminatePlayers()
    world2.eliminatePlayers()
    world3.eliminatePlayers()

    val correct1: ListBuffer[Player] = ListBuffer(player1)
    val correct2: ListBuffer[Player] = ListBuffer(player3, player4)
    val correct3: ListBuffer[Player] = ListBuffer()

    assert(world1.players == correct1)
    assert(world2.players == correct2)
    assert(world3.players == correct3)
  }
}
