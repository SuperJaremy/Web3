function redraw(data){
    if(data===undefined || data.status === "success") {
        let R = document.getElementById("dotForm:r").value;
        R = Number.parseFloat(R);
        if (document.getElementById("dotForm:rErr").innerText===""&&!isNaN(R)) {
            document.getElementById("svg_8").textContent = (R / 2).toPrecision(3);
            document.getElementById("svg_11").textContent = (-Math.abs(R)).toPrecision(3);
            document.getElementById("svg_12").textContent = (-Math.abs(R / 2)).toPrecision(3);
            document.getElementById("svg_16").textContent = (R / 2).toPrecision(3);
            document.getElementById("svg_17").textContent = R.toPrecision(3);
            document.getElementById("svg_18").textContent = (-Math.abs(R / 2)).toPrecision(3);
            document.getElementById("svg_19").textContent = (-Math.abs(R)).toPrecision(3);
            document.getElementById("svg_7").textContent = R.toPrecision(3);
            drawDots();
        }
    }
}

function drawDots(){
    let table = document.getElementById("hiddenTable");
    let group = document.getElementById("dots");
    group.innerHTML = "";
    for(let i=0, row; row = table.rows[i]; i++){
        let x=null,y=null,r=null,res=null;
        for(let j=0, col;col=row.cells[j];j++){
            let str = col.innerText.trim();
            let a = Number.parseFloat(str);
            if(!isNaN(a)){
                switch (j){
                    case 0:
                        x=a;
                        break;
                    case 1:
                        y=a;
                        break;
                    case 2:
                        r=a;
                        break;
                }
            }
            else if(j===3 && (str==="false"||str==="true"))
                res=str==="true";
        }
        if(x!=null&&y!=null&&r!=null&&res!=null) {
            const point = transformPointToGrid(x,y,r);
            drawDot(point.x,point.y,res,group);
        }
    }
}

function drawDot(x,y,res, group){
    const color = res ? "green" : "red";
    const child = document.createElementNS('http://www.w3.org/2000/svg',"circle");
    child.setAttribute("cx", x);
    child.setAttribute("cy", y);
    child.setAttribute("r", "3");
    child.setAttribute("fill", color);
    group.appendChild(child);
}

function transformPointToGrid(x,y,r){
    const newX = (x/(r/130))+200;
    const newY = 200 - (y/(r/130));
    return {x:newX,y:newY};
}

function catchClick(){
    let picture =  document.getElementById("svgpic");
    picture.addEventListener('mousedown',function (e){
        const point = getCursorPosition(picture,e);
        const r=document.getElementById("dotForm:r").value;
        const newPoint = transformPointToCoordinates(point.x,point.y,r);
        if(newPoint!=null){
            document.getElementById("hiddenForm:hiddenX").value=newPoint.x;
            document.getElementById("hiddenForm:hiddenY").value=newPoint.y;
            document.getElementById("hiddenForm:hiddenR").value=r;
            document.getElementById("hiddenForm:hiddenSubmit").click();
        }
    })
}

function getCursorPosition(canvas, event){
    const rect = canvas.getBoundingClientRect()
    const x = event.clientX - rect.left
    const y = event.clientY - rect.top
    return {x: x, y: y};
}

function transformPointToCoordinates(x,y,r){
    if(r===undefined||r===''||r==null) {
        alert("R is not set");
        return null;
    }
    const newX = (x-200)*(r/130);
    const newY = -(y-200)*(r/130);
    return {x:newX, y: newY, r:r};
}