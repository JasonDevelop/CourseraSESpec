// write your code here
function swapRedGreen(pixel){
    var red = pixel.getRed();
    var green  = pixel.getGreen();
    pixel.setRed(green);
    pixel.setGreen(red);
}

var image = new SimpleImage("chapel.png");
print(image);
for(var p of image.values()){
    swapRedGreen(p);
}
print(image);
function moreRed(pixel, value){
    red = pixel.getRed();
    red += value;
    if(red <= 255){
        pixel.setRed(red);
    }
    else{
        pixel.setRed(255);
    }
}

for(var p of image.values()){
    moreRed(p, 300);
}
print(image);

function setBlack(pixel){
    pixel.setRed(0);
    pixel.setGreen(0);
    pixel.setBlue(0);
}

for(var p of image.values()){
    setBlack(p);
}
print(image);


var image = new SimpleImage("chapel.png");

function pixelOnEdge(pixel, image, borderWidth){
    var x = pixel.getX();
    var y = pixel.getY();
    var width = image.getWidth();
    var height = image.getHeight();
    if(x < borderWidth || x > width - borderWidth || y < borderWidth || y > height - borderWidth){
        return true;
    }
    return false;
}

for(var p of image.values()){
    if(pixelOnEdge(p, image, 10)){
        setBlack(p);
    }
}
print(image);










