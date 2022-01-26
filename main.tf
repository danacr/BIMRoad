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

data "google_iam_policy" "noauth" {
  binding {
    role = "roles/run.invoker"
    members = [
      "allUsers",
    ]
  }
}

resource "google_cloud_run_service_iam_policy" "noauth" {
  location = google_cloud_run_service.default.location
  project  = google_cloud_run_service.default.project
  service  = google_cloud_run_service.default.name

  policy_data = data.google_iam_policy.noauth.policy_data
}
