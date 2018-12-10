import { ModalserviceService } from './../modalservice.service';
import { Component, OnInit, Input, ElementRef, OnDestroy } from '@angular/core';

@Component({
  selector: 'app-jw-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.css']
})
export class ModalComponent implements OnInit, OnDestroy {
  @Input() id: string;
  private element: any;

  constructor(private modalService: ModalserviceService, private el: ElementRef) {
      this.element = el.nativeElement;
  }

  ngOnInit(): void {
      const modal = this;

      // ensure id attribute exists
      if (!this.id) {
          console.error('modal must have an id');
          return;
      }

      // move element to bottom of page (just before </body>) so it can be displayed above everything else
      document.body.appendChild(this.element);

      // close modal on background click
      this.element.addEventListener('click', function (e: any) {
          if (e.target.className === 'app-jw-modal') {
              modal.close();
          }
      });

      // add self (this modal instance) to the modal service so it's accessible from controllers
      this.modalService.add(this);
  }

  // remove self from modal service when directive is destroyed
  ngOnDestroy(): void {
      this.modalService.remove(this.id);
      this.element.remove();
  }

  // open modal
  open(): void {
      this.element.style.display = 'block';
      document.body.classList.add('app-jw-modal-open');
  }

  // close modal
  close(): void {
      this.element.style.display = 'none';
      document.body.classList.remove('app-jw-modal-open');
  }
}
