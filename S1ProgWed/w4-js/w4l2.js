// write your code here
var startImage = new SimpleImage("chapel.png");
var hideImage = new SimpleImage("drewgreen.png");
//print(startImage);
//print(hideImage);
function pixChange(pixval){
    var x = Math.floor(pixval / 16) * 16;
    return x;
}
function chop2hide(image){
    for(var p of image.values()){
        p.setRed(pixChange(p.getRed()));
        p.setGreen(pixChange(p.getGreen()));
        p.setBlue(pixChange(p.getBlue()));
    }
    return image;
}
//print(chop2hide(startImage));

function shift(im){
  var nim = new SimpleImage(im.getWidth(), 
                            im.getHeight());
  for(var px of im.values()){
    var x = px.getX();
    var y = px.getY();
    var npx = nim.getPixel(x,y);
    npx.setRed(Math.floor(px.getRed()/16));
    npx.setGreen(Math.floor(px.getGreen()/16));
    npx.setBlue(Math.floor(px.getBlue()/16));
  }
  return nim;
}

//print(shift(hideImage));

function combine(start, hide){
    var nstart = chop2hide(start);
    var nhide = shift(hide);
    nhide.setSize(nstart.getWidth(), nstart.getHeight());
    var nim = new SimpleImage(nstart.getWidth(), nstart.getHeight());
    for(var p of nstart.values()){
        var x = p.getX();
        var y = p.getY();
        var np = nim.getPixel(x, y);
        var hp = nhide.getPixel(x, y);
        np.setRed(p.getRed() + hp.getRed());
        np.setGreen(p.getGreen() + hp.getGreen());
        np.setBlue(p.getBlue() + hp.getBlue());
    }
    return nim;
}

var steganography = combine(startImage, hideImage);
print(steganography);

function conv(val){
    var outVal = (val - Math.floor(val / 16) * 16) *16;
    return outVal;
}

function extract(im){
    var outIm = new SimpleImage(im.getWidth(), im.getHeight());
    for(var p of im.values()){
        var x = p.getX();
        var y = p.getY();
        var ox = outIm.getPixel(x, y);
        ox.setRed(conv(p.getRed()));
        ox.setGreen(conv(p.getGreen()));
        ox.setBlue(conv(p.getBlue()));
    }
    return outIm;
}

print(extract(steganography));

// enlarge
// write your code here
var im = new SimpleImage("chapel.png");
print(im);
function enlarge(im){
    var nim = new SimpleImage(im.getWidth() * 2, im.getHeight() * 2);
    for(var p of nim.values()){
        var x = p.getX();
        var y = p.getY();
        nim.setPixel(x, y, im.getPixel(Math.floor(x/2), Math.floor(y/2)));
    }
    return nim;
}
print(enlarge(im));

// duplicate
// write your code here

var im = new SimpleImage("chapel.png");
print(im);
var oi = new SimpleImage(im.getWidth() * 2, im.getHeight() * 2);
//print(oi);
var w = im.getWidth();
var h = im.getHeight();
//print(w,h);
for(p of oi.values()){
    var xOut = p.getX();
    var yOut = p.getY();
    var mx = 0;
    var my = 0;
    var width = im.getWidth();
    var height = im.getHeight();
    var xIn = xOut;var yIn = yOut;
    if (xIn >= width) xIn = xIn - width;
    if (yIn >=height) yIn = yIn - height;
    var ip = im.getPixel(xIn, yIn);
    oi.setPixel(xOut, yOut, ip);
}

print(oi);

// end

// create ima
var im = new SimpleImage(500, 500);
function dist(pixel, x, y){
    var dx = pixel.getX() - x;
    var dy = pixel.getY() - y;
    return Math.sqrt(dx * dx + dy * dy);
}

for(var p of im.values()){
    if(dist(p, 100, 100) < 50){
        p.setRed(255 - 7*dist(p, 100, 100));
    }
    else if(dist(p, 300, 300) < 100){
        p.setGreen(255 - 3*dist(p, 300, 300));
    }


}

print(im);
//