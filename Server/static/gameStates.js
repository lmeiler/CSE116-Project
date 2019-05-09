var keyStates = {
    "w": false,
    "a": false,
    "s": false,
    "d": false
};

function setState(key, toSet){
    if(keyStates[key] !== toSet){
        keyStates[key] = toSet;
        console.log(keyStates+"hiiii")
        socket.emit('keyStates', JSON.stringify(keyStates));
        console.log(keyStates)

    }
}

function handleEvent(event, toSet){
    if(event.key === "w" || event.key === "ArrowUp"){
        setState("w", toSet);
    }else if(event.key === "a" || event.key === "ArrowLeft"){
        console.log(keyStates)
        setState("a", toSet);
    }else if(event.key === "s" || event.key === "ArrowDown"){
        setState("s", toSet);
    }else if(event.key === "d" || event.key === "ArrowRight"){
        setState("d", toSet);
    }
}




document.addEventListener("keydown", function (event) {
    console.log("event handle")
    handleEvent(event, true);
});

document.addEventListener("keyup", function (event) {
    handleEvent(event, false);
});