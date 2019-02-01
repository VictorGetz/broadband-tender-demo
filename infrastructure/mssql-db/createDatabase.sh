echo "Create Ressource Group"
#az group create --name "broadband-tender-demo-db" --location westeurope

echo "Create Database Server"
az sql server create --name "broadband-tender-demo" --resource-group "broadband-tender-demo-db" --location westeurope --admin-user "dbAdmin" --admin-password "SECRET"

echo "Create Database"
az sql db create --resource-group "broadband-tender-demo-db" --server "broadband-tender-demo-dbserver" --name "broadband-tender-demo-database" --service-objective S0