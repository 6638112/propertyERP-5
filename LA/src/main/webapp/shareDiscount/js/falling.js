
/* Define the number of element to be used in the animation */
const ColourBarNumber = 36;

/* 
    Called when the page is completely loaded.
*/
function init()
{
	/* Get a reference to the element that will contain the element */
    var container = document.getElementById('fallingContainer');
    /* Fill the empty container with new elements */
    for (var i = 0; i < ColourBarNumber; i++) 
    {
		container.appendChild(createColourBar());
    }
}


/*
    Receives the lowest and highest values of a range and
    returns a random integer that falls within that range.
	Math.random()  生成0和1之间的随机小数
	Math.random() * 7 生成0和7之间的随机小数
	Math.random() * 7 + 1生成0和8之间的随机小数
	Math.floor(Math.random() * 7 + 1)生成0和8之间的随机整数,注意是整数，不是小数
*/
function randomInteger(low, high)
{
    return low + Math.floor(Math.random() * (high - low));
}
/*
   Receives the lowest and highest values of a range and
   returns a random float that falls within that range.
*/
function randomFloat(low, high)
{
    return low + Math.random() * (high - low);
}
/*
    Receives a number and returns its CSS pixel value.
*/
function pixelValue(value)
{
    return value + 'px';
}
/*
    Returns a duration value for the falling animation.
*/
function durationValue(value)
{
    return value + 's';
}

//创建彩带
function createColourBar()
{
    var leafDiv = document.createElement('div');
    var image = document.createElement('img');
    
    image.src = 'images/colourBar' + randomInteger(1, 8) + '.png';
    
	leafDiv.className = 'ColourBar';
    leafDiv.style.top = "-100px";

    leafDiv.style.left = pixelValue(randomInteger(0, 600));

    var spinAnimationName = (Math.random() < 0.5) ? 'clockwiseSpin' : 'counterclockwiseSpinAndFlip';
    
    leafDiv.style.webkitAnimationName = 'fade, drop';
    image.style.webkitAnimationName = spinAnimationName;

    var fadeAndDropDuration = durationValue(randomFloat(2, 8));
    
    var spinDuration = durationValue(randomFloat(1, 3));

    leafDiv.style.webkitAnimationDuration = fadeAndDropDuration + ', ' + fadeAndDropDuration;

    var leafDelay = durationValue(randomFloat(0, 1));
    leafDiv.style.webkitAnimationDelay = leafDelay + ', ' + leafDelay;

    image.style.webkitAnimationDuration = spinDuration;

    leafDiv.appendChild(image);


    return leafDiv;
}
//文档加载完成后执行
window.onload = init();
