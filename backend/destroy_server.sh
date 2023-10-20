TOKEN=$(cat ../.linode_token)

LINODES=$(curl -H "Authorization: Bearer $TOKEN" \
	https://api.linode.com/v4/linode/instances)

# Find flotteLotte_backend
LINODEID=$(echo $LINODES | jq '.data.[] | select(.label=="flotteLotte_backend") | .id')

curl -H "Authorization: Bearer $TOKEN" \
     -X DELETE https://api.linode.com/v4/linode/instances/$LINODEID

