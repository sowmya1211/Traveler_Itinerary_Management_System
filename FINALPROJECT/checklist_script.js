function fn1(cid){
  document.getElementById("myInput").focus();
  // Create a "close" button and append it to each list item
  var myNodelist = document.getElementsByTagName("LI");
  var i;
  for (i = 0; i < myNodelist.length; i++) {
    var span = document.createElement("SPAN");
    var txt = document.createTextNode("\u00D7");
    span.className = "close";
    span.appendChild(txt);
    myNodelist[i].appendChild(span);
  }

  // Click on a close button to hide the current list item
  var close = document.getElementsByClassName("close");
  var i;
  for (i = 0; i < close.length; i++) {
    close[i].onclick = function() {
      var div = this.parentElement;
      div.style.display = "none";
      delitem(div.id, cid);
    }
  }

  // Add a "checked" symbol when clicking on a list item
  var list = document.querySelector('ul');
  list.addEventListener('click', function(ev) {
    if (ev.target.tagName === 'LI') {
      ev.target.classList.toggle('checked');
      //window.alert(ev.target.id + cid + ev.target.classList.contains("checked"));
      var xhhtp = new XMLHttpRequest();
      xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
          this.responseText;
        }
      }
      if(ev.target.classList.contains("checked")){
        xhttp.open("GET", "HideChecklistItem?cid="+cid+"&cno="+ev.target.id+"&hide="+1, true);
      }
      else{
        xhttp.open("GET", "HideChecklistItem?cid="+cid+"&cno="+ev.target.id+"&hide="+0, true);
      }
      xhttp.send();
    }
  }, false);
}

function delitem(cno, cid){
  
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      this.responseText;
      get_contents(paramValue, cid);
    }
  }
  xhttp.open("GET", "DelChecklistItem?cid="+cid+"&cno="+cno, true);
  xhttp.send();
}

// Create a new list item when clicking on the "Add" button
function newElement(cid) {
  var inputValue = document.getElementById("myInput").value;
  if (inputValue === '') {
    alert("You must write something!");
  }
  else{
    document.getElementById("myInput").value = "";
    var cno = document.getElementsByTagName("li").length;
    additem(cid, (cno+1), inputValue);
  }
  
}

function additem(cid, cno, inputValue){
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {  
    if (this.readyState == 4 && this.status == 200) {
      this.responseText;
      get_contents(paramValue, cid);
    }
  }
  xhttp.open("GET", "AddItem?cid="+cid+"&cno="+cno+"&name="+inputValue, true);
  xhttp.send();
}