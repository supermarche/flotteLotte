TOKEN=$(cat ../.linode_token)

# Create key
ssh-keygen -o -a 100 -t ed25519 -f .flotteLotte -N ""
PUBKEY=$(cat .flotteLotte.pub)

curl -X POST https://api.linode.com/v4/linode/instances \
    -H "Authorization: Bearer $TOKEN" -H "Content-type: application/json" \
    -d '{"type": "g6-nanode-1", "region": "eu-central", "image": "linode/debian11", "root_pass": "xc#[o(!Hf#JyB9hNsZ&e!bJ(+[]9E", "label": "flotteLotte_backend", "authorized_keys": [ "'"$PUBKEY"'" ]}'
