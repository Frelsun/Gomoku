const BOARD_LENGTH = 19;
const WIN_CONDITION = 5;
let board = [];
let currentPlayer = '●';
let gameWon = false;

const gameBoard = document.getElementById('gameBoard');
const statusDisplay = document.getElementById('status');

function initializeBoard() {
    console.log("init()");
    board = Array.from({ length: BOARD_LENGTH }, () => Array(BOARD_LENGTH).fill(null));
    gameBoard.innerHTML = '';
    for (let row = 0; row < BOARD_LENGTH; row++) {
        for (let col = 0; col < BOARD_LENGTH; col++) {
            const cell = document.createElement('div');
            cell.classList.add('cell');
            cell.dataset.row = row;
            cell.dataset.col = col;
            cell.addEventListener('click', handleCellClick);
            gameBoard.appendChild(cell);
        }
    }
    statusDisplay.textContent = `Player ${currentPlayer === '●' ? '1' : '2'}'s turn`;
}

function handleCellClick(event) {
    console.log("clickevent");
    if (gameWon) {
        return;
    }

    const row = parseInt(event.target.dataset.row);
    const col = parseInt(event.target.dataset.col);

    if (!board[row][col]) {
        board[row][col] = currentPlayer;
        event.target.textContent = currentPlayer;

        if (checkWin(row, col)) {
            let timer = window.setInterval(tick, 2000);
            gameWon = true;
            statusDisplay.textContent = `Player ${currentPlayer === '●' ? '1' : '2'} wins!`;
            
        } else {
            currentPlayer = currentPlayer === '●' ? '○' : '●';
            statusDisplay.textContent = `Player ${currentPlayer === '●' ? '1' : '2'}'s turn`;
        }
    }
}

function checkWin(row, col) {
    return (
        checkDirection(row, col, 1, 0) ||  // horizontal
        checkDirection(row, col, 0, 1) ||  // vertical
        checkDirection(row, col, 1, 1) ||  // diagonal bottom-left to upper-right
        checkDirection(row, col, 1, -1)    // diagonal bottom-right to upper-left
    );
}

function checkDirection(row, col, rowDir, colDir) {
    let count = 1;

    for (let i = 1; i < WIN_CONDITION; i++) {
        const newRow = row + i * rowDir;
        const newCol = col + i * colDir;
        if (newRow < 0 || newRow >= BOARD_LENGTH || newCol < 0 || newCol >= BOARD_LENGTH || board[newRow][newCol] !== currentPlayer) {
            break;
        }
        count++;
    }

    for (let i = 1; i < WIN_CONDITION; i++) {
        const newRow = row - i * rowDir;
        const newCol = col - i * colDir;
        if (newRow < 0 || newRow >= BOARD_LENGTH || newCol < 0 || newCol >= BOARD_LENGTH || board[newRow][newCol] !== currentPlayer) {
            break;
        }
        count++;
    }

    return count >= WIN_CONDITION;
}

function tick()
{
    console.log("tick()");
    
    let red = parseInt(Math.random() * 256);
    let green = parseInt(Math.random() * 256);
    let blue = parseInt(Math.random() * 256);
    
    document.body.style.backgroundColor = "rgb(" + red + ", " + green + ", " + blue + ")";
    
}

function restartGame() {
    console.log("restart()");
    currentPlayer = '●';
    gameWon = false;
    initializeBoard();
    clearInterval(timer);
    timer = null;
    document.body.style.backgroundColor = "#cc98fa";
}


initializeBoard();