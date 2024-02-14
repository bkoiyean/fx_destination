
function initMap() {
    const map = new google.maps.Map(document.getElementById("map"), {
      zoom: 3,
      center: hh,
    });
    
    // Create an array of alphabetical characters used to label the markers.
    const labels = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    // Add some markers to the map.
    const infoWindow = new google.maps.InfoWindow();
    const markers = [];

    for (i = 0; i < locations.length; i++) {
        const marker = new google.maps.Marker({
          position: locations[i],
          map: map
        });
        markers.push(marker);
        google.maps.event.addListener(marker, 'click', (function(marker, i) {
          return function() {
            infoWindow.setContent(labels[i]);
            infoWindow.open(map, marker);
          }
        })(marker, i));
        
    }

    // Add a marker clusterer to manage the markers.
    new MarkerClusterer({ markers, map});
   
}

// All locations:
const hh = { lat: -33.89520382629208, lng: 150.93624069287293 };
const hc = { lat: -33.8840801308621, lng: 150.9262480386949 };
const hb = { lat: -33.91824820135931, lng: 151.0328912816783 };
const fefx = { lat: -33.87494537829888, lng: 151.21882814187086 };
const hp = { lat: -37.95112628922732, lng: 145.15233595871462 };
const hn = { lat: -37.74263208354723, lng: 144.8003312697461 };
const hf = { lat: -37.79960203178372, lng: 144.899940891333 };
const hs = { lat: -37.78460791371533, lng: 144.83233987635612 };
const che = { lat: -27.38321011868271, lng: 153.03232058876625 };
const bri = { lat: -27.467005395660987, lng: 153.02663364197176 };
const tai = { lat: -27.34945626725601, lng: 153.04854320614282 };
const sun = { lat: -27.610713017117654, lng: 153.05559912564536 };
const hg = { lat: -31.835028934241198, lng: 115.82990953920238 };
const fead = { lat: -34.92949052630635, lng: 138.59668566230025 };
const cas = { lat: -12.373639371172825, lng: 130.8811309294567 };
const dar = { lat: -12.46373149645906, lng: 130.83972271216038 };

const locations = [
    hh,hc,hb,fefx,hp,hn,hf,hs,che,bri,tai,sun,hg,fead,cas,dar
];


window.initMap = initMap;
