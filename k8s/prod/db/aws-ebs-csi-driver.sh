#!/bin/bash

echo "STARTING - Setting AWS EBS CSI Driver.."

kubectl apply -k "github.com/kubernetes-sigs/aws-ebs-csi-driver/deploy/kubernetes/overlays/stable/?ref=release-1.32"

echo "END - AWS EBS CSI Driver was defined."