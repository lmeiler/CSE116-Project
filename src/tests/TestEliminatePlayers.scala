//package tests
//
//import main._
//import physics._
//import org.scalatest._
//
//class TestEliminatePlayers extends FunSuite {
//
//  test("Tests the eliminate player method") {
//    val defaultHealth: Int = 20
//    val defaultStrength: Int = 5
//    val defaultLocation: PhysicsVector = new PhysicsVector(0.0, 0.0)
//    val defaultVelocity: PhysicsVector = new PhysicsVector(0.0, 0.0)
//    val defaultOrientation: PhysicsVector = new PhysicsVector(1.0, 0.0)
//
//    val player1: Player = new Player(defaultHealth, defaultStrength, defaultLocation, defaultVelocity, defaultOrientation, username = "ReadyPlayerOne", defaultHealth)
//    val player2: Player = new Player(defaultHealth, defaultStrength, defaultLocation, defaultVelocity, defaultOrientation, username = "PlayerTwo", defaultHealth)
//    val player3: Player = new Player(0, defaultStrength, defaultLocation, defaultVelocity, defaultOrientation, username = "PlayerThree", 0  )
//    val player4: Player = new Player(defaultHealth, defaultStrength, defaultLocation, defaultVelocity, defaultOrientation, username = "PlayerFour", defaultHealth)
//    val player5: Player = new Player(0, defaultStrength, defaultLocation, defaultVelocity, defaultOrientation, username = "PlayerFive", 0)
//
//    val playerList: List[Player] = List(player1, player2, player3, player4, player5)
//    val world1: World = new World(9.8, List(), List(), playerList, List())
//    val newPlayers1: List[Player] = List(player1, player2, player4)
//    val world2: World = new World(9.8, List(), List(), newPlayers1, List())
//    val world3: World = new World(9.8, List(), List(), List(), List())
//
//    world1.eliminatePlayers()
//    world2.eliminatePlayers()
//    world3.eliminatePlayers()
//
//    assert(world1.players == newPlayers1)
//    assert(world2.players == newPlayers1)
//    assert(world3.players == List())
//  }
//}
