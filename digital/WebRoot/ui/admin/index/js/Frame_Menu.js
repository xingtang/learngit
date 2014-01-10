function showsubmenu(sid)
{
	var whichEl = document.getElementById("submenu" + sid);
	if (whichEl.style.display == "none")
	{
		whichEl.style.display="block";
	}
	else
	{
		whichEl.style.display="none";
	}
}
function doReloadFrame()
{
    //parent.frames["main"].location.reload();
    parent.frames["main"].location.href="/admin/about";
}
