package tictactoe
const val DEF_BOARD_VALUE = "_________"
class tictac {
	var gameRunned = true
	enum class battlefieldPlayer(val text: String)
	{X("X"), O("O"), PADDING("_")}
	//typealias battlefieldBoard = MutableList<MutableList<battlefieldPlayer>>
	var X = battlefieldPlayer.X
	var O = battlefieldPlayer.O
	var PADDING = battlefieldPlayer.PADDING
	var m_battlefield: MutableList<MutableList<battlefieldPlayer>>
	
	var raw_board: String = ""
	// weird name, but is ok (magic name)	
	fun initBattlefield(x: String = DEF_BOARD_VALUE): MutableList<MutableList<battlefieldPlayer>> {
	    var ret = mutableListOf<MutableList<battlefieldPlayer>>()
	    var tmp = mutableListOf<battlefieldPlayer>()

	    raw_board = x
	    // if (x.length != 9) throw( >...< )
	    var c = 1
	    for (i in x) {
		//x = readln()
		when (i) {
			'X' -> { 
				tmp.add(X)
			}
			'_' -> {
				tmp.add(PADDING)
			} else -> {
				tmp.add(O)
			}
		}
		// module by 3?
		if (c % 3 == 0) { 
		    ret.add( tmp.toMutableList() ) // subList(c-3, c)
		    tmp.clear()
		}
		c++
	    }
	    return ret.toMutableList()
	}
     init {
     	m_battlefield = initBattlefield()
     }

     fun checkBoard(board: MutableList<MutableList<battlefieldPlayer>> = m_battlefield) {
     	var XFound = false
     	var YFound = false
     	var isDraw = true
     	// Left Right. TODO("cycle for with label")
     	if (board[0][0] == X && board[0][1] == X && board[0][2] == X) XFound = true
     	else if (board[0][0] == O && board[0][1] == O && board[0][2] == O) YFound = true

     	if (board[1][0] == X && board[1][1] == X && board[1][2] == X) XFound = true
     	else if (board[1][0] == O && board[1][1] == O && board[1][2] == O) YFound = true
     	
     	if (board[2][0] == X && board[2][1] == X && board[2][2] == X) XFound = true
     	else if (board[2][0] == O && board[2][1] == O && board[2][2] == O) YFound = true
     	// TopBottom. TODO("cycle for")
     	if (board[0][0] == X && board[1][0] == X && board[2][0] == X) XFound = true
     	else if (board[0][0] == O && board[1][0] == O && board[2][0] == O) YFound = true

     	if (board[0][1] == X && board[1][1] == X && board[2][1] == X) XFound = true
     	else if (board[0][1] == O && board[1][1] == O && board[2][1] == O) YFound = true
     	
     	if (board[0][2] == X && board[1][2] == X && board[2][2] == X) XFound = true
     	else if (board[0][2] == O && board[1][2] == O && board[2][2] == O) YFound = true
     	// krest nakrest. TODO("cycle for")
     	if (board[0][0] == X && board[1][1] == X && board[2][1] == X && board[0][2]== X) XFound = true     	
     	if (board[0][0] == O && board[1][1] == O && board[2][1] == O && board[0][2] == O) YFound = true

     	
	// TODO cycle for with labels
     	if (board[1][1] == X && board[2][2] == X && board[0][0] == X) XFound = true
     	else if (board[2][2] == O&& board[1][1] == O && board[0][0] == O) YFound = true
     	//
     	if (board[0][0] == X && board[1][1] == X && board[2][2] == X) XFound = true
     	else if (board[0][0] == O && board[1][1] == O && board[2][2] == O) YFound = true

     	if (board[1][1] == X && board[2][2] == X && board[0][0] == X) XFound = true
     	else if (board[2][2] == O&& board[1][1] == O && board[0][0] == O) YFound = true
     	for (row in board) {
     		for (column in row) {
     			if (column.text == "_") isDraw = false
     		}
     	}
     	// TODO: FIX logic in future
     	//if (raw_board == "_O_X__X_X" || raw_board == "_OOOO_X_X") println("Impossible")
     	//else
     	if (XFound && YFound) {
     		println("Impossible"); gameRunned = false
     	} else if (XFound) {
     		println("X wins"); gameRunned = false
     	} else if (YFound) {
     		println("O wins"); gameRunned = false
     	} else if (!isDraw){
     		//println("Game not finished")
     	} else {
     	 println("Draw"); gameRunned = false
	}
     	     	
     }
     fun print() {
	    println("---------")
	    for (i1 in m_battlefield) {
		print("| ")
		for (i2 in i1){
		    print(i2.text.uppercase() + " ")
	//          println(i2.name.toUpperCase()+" ")
		}
		print("|")
		println()
	    }
	    println("---------")
     }
     
     fun getXY(): Pair<Int, Int> {
     	var XY = readln().split(" ")
     	if (XY.size != 2 || XY[1].toIntOrNull() == null || XY[0].toIntOrNull() == null) {
     		println("You should enter numbers!")
     		return getXY()
     	}
     	var X = XY[1].toInt() - 1
     	var Y = XY[0].toInt() - 1
     	
     	if ( !(X in 0..2) || !(Y in 0..2) ) {
     		println("Coordinates should be from 1 to 3!")
     		return getXY()
     	}
     	
     	if (m_battlefield[Y][X] != PADDING) {
     		println("This cell is occupied! Choose another one!")
     		return getXY()
     	}
     	return Pair(X,Y)
     }
     
     fun _set(what: battlefieldPlayer, XY: Pair<Int, Int>) {
     	var (x,y) = XY
     	m_battlefield[y][x] = what
     }
     
     fun turn(what: battlefieldPlayer) {
     	if (this.gameRunned) {
	    	this.print()
	    	this._set(what, this.getXY())
	    	this.print()
	    	this.checkBoard()     
    	}
     }
}

fun main() {
    // write your code here
    var tictac = tictac() 
    while (tictac.gameRunned) {
	tictac.turn(tictac.X)
    	//
    	tictac.turn(tictac.O)
   }
}
