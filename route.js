var map = L.map('map').setView([51.146, 14.8789], 13);

L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
}).addTo(map);

L.marker([51.146, 14.8789]).addTo(map)
    .bindPopup('A pretty CSS popup.<br> Easily customizable.')
    .openPopup();

L.Control.geocoder().addTo(map);

function onMapClick(e) {
        alert("You clicked the map at " + e.latlng);
    }
    
    map.on('click', onMapClick);

    function onSetPickUp(e) {
        alert("You set the map at " + e.latlng);
    }

    function onSetPickUp() {
        alert("You set the map at" );
    }
    
    