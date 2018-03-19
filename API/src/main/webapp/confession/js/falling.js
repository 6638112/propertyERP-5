
/* Define the number of element to be used in the animation */
const flowerNumber = 10;

/* 
    Called when the page is completely loaded.
*/
function init(){
    var $eggBrake = $('.egg-brake');
	/* Get a reference to the element that will contain the element */
    var container = document.getElementById('fallingContainer');
    /* Fill the empty container with new elements */
    for (var i = 0; i < flowerNumber; i++) 
    {
		$(container).append(createFlowers());
    }
}

/*
    Receives the lowest and highest values of a range and
    returns a random integer that falls within that range.是小数
*/
function randomInteger(low, high){
    return low + Math.floor(Math.random() * (high - low));
}
/*
   Receives the lowest and highest values of a range and
   returns a random float that falls within that range.
*/
function randomFloat(low, high){
    return low + Math.random() * (high - low);
}
/*
    Receives a number and returns its CSS pixel value.
*/
function pixelValue(value){
    return value + 'px';
}
/*
    Returns a duration value for the falling animation.
*/
function durationValue(value){
    return value + 's';
}

//创建花瓣
function createFlowers(){
    var leafDiv = document.createElement('div');
    var image = document.createElement('img');
    
    image.src = '../confession/images/flower_000' + randomInteger(1, 7) + '.png';
    
	leafDiv.className = 'flowers';
    leafDiv.style.top = "-100px";

    leafDiv.style.left = pixelValue(randomInteger(0, 600));

    var spinAnimationName = (Math.random() < 0.5) ? 'clockwiseSpin' : 'counterclockwiseSpinAndFlip';
    
    leafDiv.style.webkitAnimationName = 'fade, drop';
    image.style.webkitAnimationName = spinAnimationName;

    var fadeAndDropDuration = durationValue(randomFloat(6, 10));
    
    var spinDuration = durationValue(randomFloat(1, 3));

    leafDiv.style.webkitAnimationDuration = fadeAndDropDuration + ', ' + fadeAndDropDuration;

    var leafDelay = durationValue(randomFloat(0, 1));
    leafDiv.style.webkitAnimationDelay = leafDelay + ', ' + leafDelay;

    image.style.webkitAnimationDuration = spinDuration;

    leafDiv.appendChild(image);

    return leafDiv;
}
//文档加载完成后执行
$(function (){ 
	init();
})
