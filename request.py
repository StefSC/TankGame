from urllib import request, parse
import json

# Create tank Panzer
contents = request.urlopen("http://localhost:8080/tank/new?id=Panze&health=100").read()
print(contents)

# Create tank T-34
contents = request.urlopen("http://localhost:8080/tank/new?id=T-34&health=100").read()
print(contents)

weapon = {
	"name": "cannon",
	"damage": 10,
	"range": 10
}
weapon = json.dumps(weapon)
weapon = str(weapon)
weapon = weapon.encode('utf-8')
print(weapon)

headers = {'Content-type': 'application/json'}
req = request.Request("http://localhost:8080/tank/1/add/weapon", data=weapon, headers=headers)
resp = request.urlopen(req)
req = request.Request("http://localhost:8080/tank/2/add/weapon", data=weapon, headers=headers)
resp = request.urlopen(req)

# Create tank T-34
contents = request.urlopen("http://localhost:8080/game/start/1vs2").read()
print(contents)