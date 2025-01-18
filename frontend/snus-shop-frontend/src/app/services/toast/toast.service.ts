import { Injectable } from '@angular/core';
import {ToastController} from '@ionic/angular';
import { PredefinedColors } from '@ionic/core/dist/types/interface';

@Injectable({
  providedIn: 'root'
})
export class ToastService {

  constructor(
    private toastController: ToastController
  ) {}

  async createToast(text: string, color: PredefinedColors, duration: number) {
    const toast = await this.toastController.create({
      message: text,
      color,
      duration,
      animated: true,
    });
    await toast.present();
  }
}
