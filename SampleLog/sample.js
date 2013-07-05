setLogData({
logs: [
// createImage, widht, height, alpha, red, green, blue, imageId
['createImage',100,100,255,0,0,255,0],
// clear, red, green, blue
['clear',255,0,0,0],
// drawImage, imageId, x, y
['drawImage',0,10,20],
// forceRepaint（上述の処理を画面に反映させて表示，forceRepaintが来るまでできれば画面は更新しない）
['forceRepaint'],
],
});
