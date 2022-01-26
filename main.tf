terraform {
  cloud {
    organization = "danacr"

    workspaces {
      name = "BIMRoad"
    }
  }
}

provider "google" {
  region = "europe-west4"
}


resource "google_cloud_run_service" "default" {
  name     = "bimroad"
  location = "europe-west4"
  project  = var.project

  template {
    spec {
      containers {
        image = var.image
      }
    }
  }

  traffic {
    percent         = 100
    latest_revision = true
  }

}