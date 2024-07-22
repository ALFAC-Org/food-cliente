# TENTAR: Configurando a aplicação com K8S na AWS

1. Crie o `cluster`:

```bash
eksctl create cluster \
  --name food-cluster-2 \
  --region us-east-1 \
  --nodegroup-name food-node-2 \
  --node-type t3.medium \
  --nodes 1 \
  --nodes-min 1 \
  --nodes-max 2 \
  --node-labels "app=food-node-2,environment=production"
  --service-account-role-arn arn:aws:iam::247819101266:role/AmazonEKS_EBS_CSI_DriverRole \
```

2. Crie o `nodegroup`:

```bash
eksctl create nodegroup \
  --cluster food-cluster-2 \
  --region us-east-1 \
  --name food-nodegroup \
  --node-type t3.medium \
  --nodes 1 \
  --nodes-min 1 \
  --nodes-max 2 \
  --vpc-private-subnets=subnet-1234abcd,subnet-5678efgh \
  --vpc-id=vpc-09abcdef \
  --node-security-groups=sg-0123456789abcdef0
  ```

3. Crie o `addon`:

```bash
  eksctl create addon \
  --name aws-ebs-csi-driver \
  --version latest \
  --cluster food-cluster-2 \
  --service-account-role-arn arn:aws:iam::247819101266:role/AmazonEKS_EBS_CSI_DriverRole \
  --force
  ```

4. Permita que o `nodegroup` possa acessar o `cluster`:

```bash
aws ec2 authorize-security-group-ingress \
  --group-id sg-0123456789abcdef0 \
  --protocol all \
  --port all \
  --cidr 0.0.0.0/0
```
