import { Component, ViewChild, ElementRef, Renderer2 } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  @ViewChild('container', { static: true }) containerRef!: ElementRef<HTMLDivElement>;

  toggleForm() {
    this.containerRef.nativeElement.classList.toggle('active');
  }
}