// w1
var im = new SimpleImage(500, 500);

for(var p of im.values()){
    var x = p.getX();
    var y = p.getY();
    p.setRed(x/2);
    p.setBlue(255-x/2);
    p.setGreen(y/2);
}
print(im);
// end

// w2
var im = new SimpleImage("ori.jpg");
print(im);
var w = im.getWidth();
var h = im.getHeight();
for(var p of im.values()){
    var x = p.getX();
    var y = p.getY();
    if(x < w/2 ){
        p.setRed(255);
        if( y > h/2){
            p.setBlue(255);
        }
    }
    else if(y < h/2){
        p.setGreen(255);
    }else{p.setBlue(255);}
}
print(im);
// w3
function crop(im, width, height){
    var nim = new SimpleImage(width, height);
    for(var p of nim.values()){
        var x = p.getX();
        var y = p.getY();
        nim.setPixel(x, y, im.getPixel(x, y));
    }
    return im;
}

function pixChange(pixval, bit){
    tmp = Math.pow(2, bit);
    var x = Math.floor(pixval / tmp) * tmp;
    return x;
}

function chop2hide(im, bit){
    for(var p of im.values()){
        p.setRed(pixChange(p.getRed(), bit));
        p.setGreen(pixChange(p.getGreen(), bit));
        p.setBlue(pixChange(p.getBlue(), bit));
    }
    return im;
}

function shift(im, bit){
  var tmp = Math.pow(2, 8 - bit);
  var nim = new SimpleImage(im.getWidth(), 
                            im.getHeight());
  for(var px of im.values()){
    var x = px.getX();
    var y = px.getY();
    var npx = nim.getPixel(x,y);
    npx.setRed(Math.floor(px.getRed()/tmp));
    npx.setGreen(Math.floor(px.getGreen()/tmp));
    npx.setBlue(Math.floor(px.getBlue()/tmp));
  }
  return nim;
}

function combine(start, hide, bit){
    var nstart = chop2hide(start, bit);
    var nhide = shift(hide, bit);
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

function conv(val, bit){
    var tmp = Math.pow(2, bit);
    var outVal = (val - Math.floor(val / tmp) * tmp) *tmp;
    return outVal;
}

function extract(im, bit){
    var outIm = new SimpleImage(im.getWidth(), im.getHeight());
    for(var p of im.values()){
        var x = p.getX();
        var y = p.getY();
        var ox = outIm.getPixel(x, y);
        ox.setRed(conv(p.getRed(), bit));
        ox.setGreen(conv(p.getGreen(), bit));
        ox.setBlue(conv(p.getBlue(), bit));
    }
    return outIm;
}

var startImage = new SimpleImage("astrachan.jpg");
var hideImage = new SimpleImage("duvall.jpg");
var w = Math.min(startImage.getWidth(), hideImage.getWidth());
var h = Math.min(startImage.getHeight(), hideImage.getHeight());
hideImage = crop(hideImage, w, h);
startImage = crop(startImage, w, h);
print("start");
print(startImage);
print("hide");
print(hideImage);
var steganography = combine(startImage, hideImage, 4);
print("combined");
print(steganography);
print("extracted");
print(extract(steganography, 4));
//