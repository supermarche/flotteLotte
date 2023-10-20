TOKEN=$(cat ../.linode_token)

curl -X POST https://api.linode.com/v4/linode/instances \
    -H "Authorization: Bearer $TOKEN" -H "Content-type: application/json" \
    -d '{"type": "g6-nanode-1", "region": "eu-central", "image": "linode/debian11", "root_pass": "x{c#[o(!Hf#JyB9hNsZ&e!bJ(+[]9E", "label": "flotteLotte_backend"}'
