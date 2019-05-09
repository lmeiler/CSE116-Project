//package tests
//
//import main._
//import objects.{Projectile, Shoot}
//import physics._
//import org.scalatest._
//
//class TestShooting extends FunSuite {
//  test("Test shoot without update"){
//    val boundary1 = new Boundary(new PhysicsVector(20, 150), new PhysicsVector(40, 150))
//    val boundary2 = new Boundary(new PhysicsVector(40, 200), new PhysicsVector(60, 200))
//    val ground = new Boundary(new PhysicsVector(0, 0), new PhysicsVector(400, 0))
//
//    var boundaries: List[Boundary] = List(boundary1, boundary2, ground)
//    val world = new World(boundaries)
//    var player1: Player = new Player(new PhysicsVector(10, 0), new PhysicsVector(0, 0), "Player1", world)
//    var player2: Player = new Player(new PhysicsVector(70, 0), new PhysicsVector(0, 0), "Player2", world)
//
//    world.players += player1
//    player1.shoot()
//    //player2.leftPressed()
//    player2.shoot()
//   // world.update(1.0)
//
//    val projectile1: Projectile = new Projectile(new PhysicsVector(11, 50), new PhysicsVector(15, 0))
//    val projectile2: Projectile = new Projectile(new PhysicsVector(71, 50), new PhysicsVector(15, 0))
//
//    assert(world.projectiles(0).location.x == 11)
//    assert(world.projectiles(0).location.y == 50)
//    assert(world.projectiles(0).velocity.x == 200)
//    assert(world.projectiles(0).velocity.y == 0)
//    assert(world.projectiles(1).location.x == 71)
//    assert(world.projectiles(1).location.y == 50)
//    assert(world.projectiles(1).velocity.x == 200)
//    assert(world.projectiles(1).velocity.y == 0)
//
//
//  }
//
//    test("Test shoot with update"){
//      val boundary1 = new Boundary(new PhysicsVector(20, 150), new PhysicsVector(40, 150))
//      val boundary2 = new Boundary(new PhysicsVector(40, 200), new PhysicsVector(60, 200))
//      val ground = new Boundary(new PhysicsVector(0, 0), new PhysicsVector(400, 0))
//
//      var boundaries: List[Boundary] = List(boundary1, boundary2, ground)
//      val world = new World(boundaries)
//      var player1: Player = new Player(new PhysicsVector(10, 0), new PhysicsVector(0, 0), "Player1", world)
//      var player2: Player = new Player(new PhysicsVector(70, 0), new PhysicsVector(0, 0), "Player2", world)
//
//      world.players += player1
//      player1.shoot()
//      player2.leftPressed()
//      player2.shoot()
//      world.update(1.0)
//
//      val projectile1: Projectile = new Projectile(new PhysicsVector(26, 50), new PhysicsVector(15, 0))
//      val projectile2: Projectile = new Projectile(new PhysicsVector(54, 50), new PhysicsVector(-15, 0))
//
//      assert(world.projectiles(0).location.x == 211)
//      assert(world.projectiles(0).location.y == 50)
//      assert(world.projectiles(0).velocity.x == 200)
//      assert(world.projectiles(0).velocity.y == 0)
//      assert(world.projectiles(1).location.x == -131)
//      assert(world.projectiles(1).location.y == 50)
//      assert(world.projectiles(1).velocity.x == -200)
//      assert(world.projectiles(1).velocity.y == 0)
//
//
//    }

//}